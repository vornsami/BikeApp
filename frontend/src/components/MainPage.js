import React, { useEffect, useState }  from 'react'

import Box from '@material-ui/core/Box'

import getPaths from './services/network'
import PathList from './PathList'
import FailPage from './FailPage'

const MainPage = () => {
  const [paths, setPaths] = useState([])
  var sortBy = 'Distance'
  const setSort = newSort => {
    sortBy = newSort
  }

  useEffect(() => {
    async function fetchData() {
      const initialPaths = await getPaths(sortBy, 50, 0)
      setPaths(initialPaths)
    }
    try {
      fetchData()
    } catch (e) {
      console.error(e)
    }
  }, [sortBy])

  try {
    return <Box>
        {paths !== undefined && <PathList paths={paths} setSort={setSort}/>}
      </Box>
  } catch (e) {
    return <FailPage/>
  }
}

export default MainPage