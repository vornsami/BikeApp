
import React from 'react'
import Grid from '@material-ui/core/Grid'
import Button from '@material-ui/core/Button'


const BikePathHeader = ({setSort, sortBy}) => {

  const HeaderElement = props => {
    const sort = (props.sort)? props.sort : "Distance"
    return <Button onClick= { () => setSort(sort)}>
             {props.children} {(sortBy === sort) ? <b>↓</b> : ""}
        </Button>
  }

  return <Grid container item spacing={2} xs={12}>
      <Grid item xs>
        <HeaderElement sort={'Departure station id'}>Departure station id</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Departure station name'}>Departure station name</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Departure'}>Departure time</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Return station id'}>Return station id</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Return station name'}>Return station name</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Return'}>Return time</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Distance'}>Distance (m)</HeaderElement>
      </Grid>
      <Grid item xs>
        <HeaderElement sort={'Duration'}>Duration (s)</HeaderElement>
      </Grid>
    </Grid>
}

export default BikePathHeader