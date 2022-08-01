
import React from 'react'
import Grid from '@material-ui/core/Grid'


const BikePath = ({path}) => {
  
  return <Grid container>
      <Grid item xs='1'>
        {path['Departure station id']}
      </Grid>
      <Grid item xs='1'>
        {path['Departure station name']}
      </Grid>
      <Grid item xs='1'>
        {new Date(path['Departure']).toUTCString()}
      </Grid>
      <Grid item xs='1'/>
      <Grid item xs='1'>
        {path['Return station id']}
      </Grid>
      <Grid item xs='1'>
        {path['Return station name']}
      </Grid>
      <Grid item xs='1'>
        {new Date(path['Return']).toUTCString()}
      </Grid>
      <Grid item xs='1'/>
      <Grid item xs='1'>
        {path['Distance']}
      </Grid>
      <Grid item xs='1'>
        {path['Duration']}
      </Grid>
    </Grid>
}

export default BikePath