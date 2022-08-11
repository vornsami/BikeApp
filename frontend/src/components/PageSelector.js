import React from 'react'
import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'


const PageSelector = ({page}) => {
  return <Grid container item spacing={2}>
      {page && page > 1 && <Grid item xs>
        <Button>← Last Page</Button>
      </Grid> || <Grid item xs>
         
      </Grid>

      }
      <Grid item xs>
        <Button>Next Page →</Button>
      </Grid>
    </Grid>
}

export default PageSelector